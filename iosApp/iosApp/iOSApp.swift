import SwiftUI
import Foundation
import ComposeApp
import shared


@main
struct iOSApp: App {

    let appContainer: ObservableValueWrapper<AppContainer>

    init() {
        self.appContainer = ObservableValueWrapper<AppContainer>(
            value: AppContainer()
        )
    }
    
    var body: some Scene {
        WindowGroup {
            
            ViewModelStoreOwnerProvider {
                TodoAppScreenView()
            }
            .environmentObject(appContainer)
           
        }
    }
}

struct HelloView: View {
    var body: some View {
        ZStack(alignment: .center) {
            Text("Hello, World!")
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity) // Make ZStack take up the whole screen
    }
}
