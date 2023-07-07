//
//  PhotoListFeature.swift
//  iosApp
//
//  Created by tounyu on 2023/07/08.
//  Copyright © 2023 tounyu.dev. All rights reserved.
//

import Foundation
import ComposableArchitecture
import shared

struct PhotoListFeature: ReducerProtocol {
    @Dependency(\.mainQueue) var mainQueue
    
    enum Action: Equatable {
        case authorizationPhotos
        case getPhotoList
        case getPhotoListResponse([Photo])
        case error
    }
    
    struct State: Equatable {
        var photoList: [Photo] = []
    }
    
    func reduce(into state: inout State, action: Action) -> EffectTask<Action> {
        switch action {
            
        case .authorizationPhotos:
            return .run(priority: TaskPriority.high) { send in
                let authLocalPhotoUseCase : AuthLocalPhotoUseCase = koin.get()
                let result = try await authLocalPhotoUseCase.invoke(param: KotlinUnit())
                switch(Result<KotlinBoolean>(result)) {
                case let .success(isAuthorized):
                    if(isAuthorized.boolValue) {
                        await send(.getPhotoList)
                    }
                case let .error(t):
                    print(t.printStackTrace())
                    await send(.error)
                }
            }
        case .getPhotoList:
            return .run { send in
                let getLocalPhotoUseCase : GetLocalPhotoUseCase = koin.get()
                let result = try await getLocalPhotoUseCase.invoke(param: KotlinUnit())
                switch(Result<PhotoList>(result)) {
                case let .success(photoList):
                    print(photoList)
                    await send(.getPhotoListResponse(photoList.items))
                case .error:
                    await send(.error)
                }
            }
            
        case let .getPhotoListResponse(photoList):
            state.photoList = photoList
            return .none
            
        case .error:
            return .none
        }
    }
}
