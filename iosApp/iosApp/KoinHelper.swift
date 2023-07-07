//
//  KoinHelper.swift
//  iosApp
//
//  Created by tounyu on 2023/07/08.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

func startKoin() {
    _koin = KoinHelperKt.doInitKoin().koin
}

private var _koin: Koin_coreKoin?

var koin: Koin_coreKoin {
    return _koin!
}

extension Koin_coreKoin {
    func get<T: CoroutineUseCase<P, R>, P, R>() -> T {
        return koin.getDependency(objCClass: T.self) as! T
    }
}
