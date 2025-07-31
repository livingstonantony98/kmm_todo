import SwiftUI

import ComposeApp

@main
struct iOSApp: App {

    init(){
        Platform_iosKt.getNativeResponse(input: <#T##Int32#>)
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}