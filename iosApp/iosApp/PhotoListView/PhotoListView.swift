//
//  PhotoListView.swift
//  iosApp
//
//  Created by tounyu on 2023/07/08.
//  Copyright © 2023 tounyu.dev. All rights reserved.
//

import Foundation
import ComposableArchitecture
import SwiftUI
import shared

struct PhotoListView: View {
  let store: StoreOf<PhotoListFeature>

  var body: some View {
    WithViewStore(self.store, observe: { $0 }) { viewStore in
        ScrollView {
          ForEach(viewStore.photoList, id: \.self) { photo in
            Image(uiImage: photo.uiImage)
              .resizable()
              .padding(.horizontal, 24)
              .padding(.top, 24)
              .aspectRatio(contentMode: .fit)
          }
        }
        .onAppear {
          viewStore.send(.authorizationPhotos)
        }
    }
  }
}
