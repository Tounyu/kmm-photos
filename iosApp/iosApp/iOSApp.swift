import SwiftUI
import ComposableArchitecture
import shared

@main
struct iOSApp: App {

    init() {
        startKoin()
    }

	var body: some Scene {
		WindowGroup {
			PhotoListView(store: Store(initialState: PhotoListFeature.State()) {
                PhotoListFeature()
              })
		}
	}
}
