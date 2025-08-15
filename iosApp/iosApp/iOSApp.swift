import SwiftUI


@main
struct iOSApp: App {

    var body: some Scene {
        WindowGroup {
            HelloView()
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
