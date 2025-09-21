//
// Created by Philosopher on 08/08/25.
//


import Foundation
import SwiftUI
import shared



// Define this in a relevant place, often with your ViewModel or model types
enum UiState {
    case loading
    case success(data: [Todo])
    case error(message: String)
}


struct TodoAppScreenView: View {
    
    
    // @StateObject ensures the ViewModel's lifecycle is tied to this view's lifecycle.
    // Use @ObservedObject if the ViewModel is created and managed by a parent and passed in.
//    @StateObject private var viewModel: MyViewModel = MyViewModel()

    @EnvironmentObject var viewModelStoreOwner: IOSViewModelStoreOwner
    @EnvironmentObject var appContainer: ObservableValueWrapper<AppContainer>
    
    var body: some View {
       
        let mainViewModel: MyViewmodel = viewModelStoreOwner.viewModel(
            factory: appContainer.value.todoViewModelFactory
        )
        // For iOS 16+ and more complex navigation, NavigationStack is often preferred.
        NavigationView {
            Observing(mainViewModel.todos){ item in
                print(<#T##items: Any...##Any#>)
                
            }
            ZStack { // ZStack is used here to easily center content like loading/error states.
                // Content is layered; views appearing later are on top.

              
                // Switch on the current state from the ViewModel to display appropriate UI
//                switch viewModel.uiState {
//                case .loading:
//                    VStack(spacing: 16) {
//                        ProgressView() // SwiftUI's equivalent of CircularProgressIndicator
//                            .scaleEffect(1.5) // Optional: makes the indicator larger
//                        Text("Loading todos...")
//                            .font(.headline)
//                            .foregroundColor(.secondary)
//                    }
//                case .success(let todos):
//                    if todos.isEmpty {
//                        Text("No todos found. Add Some!")
//                            .font(.title2)
//                            .multilineTextAlignment(.center)
//                            .foregroundColor(.secondary)
//                            .padding(.horizontal)
//                    } else {
//                        // Assuming TodoListContentView is defined as converted previously
//                        TodoListContentView(todos: todos)
//                    }
//                case .error(let message):
//                    // Assuming ErrorContentView is defined as converted previously
//                    ErrorContentView(message: message)
//                        .padding() // Give the error content some breathing room
//                }
//            }
//            .navigationTitle("Todo List (SwiftUI)") // Sets the title in the navigation bar
//            // .navigationBarTitleDisplayMode(.inline) // Or .large, .automatic
//            .onAppear {
//                // When the view appears, trigger the ViewModel to fetch todos.
//                // This is a common place for initial data loading.
//                viewModel.fetchTodos()
//            }

        }
        
        ZStack(alignment: .center) {
            Text("Hello, World!")
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity) 
    }
}


struct TodoListContentView: View {
    var todos: [Todo]
    var body: some View {
        List {
            ForEach(todos, id: \.id) { todo in
                TodoItemView(todo: todo)
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity) // .fillMaxSize()
        .padding(.horizontal, 16) // .padding(horizontal = 16.dp)
    }
}


struct TodoItemView: View {
    let todo: Todo

    var body: some View {
        HStack(alignment: .center, spacing: 16) {
            Image(systemName: todo.isCompleted ? "checkmark.square.fill" : "square")
                .font(.title2)
                .foregroundColor(todo.isCompleted ? .accentColor : .secondary)
                .onTapGesture {
                    print("Checkbox tapped for todo: \(todo.title)")
                }

            VStack(alignment: .leading, spacing: 4) { // Corresponds to Column
                Text(todo.title)
                    .font(.headline) // Approximate for titleMedium
                    .strikethrough(todo.isCompleted, color: .gray) // For TextDecoration.LineThrough
                    .foregroundColor(todo.isCompleted ? .gray : .primary) // .primary is like onSurface

                Text("User ID: \(todo.userId)")
                    .font(.caption) // Approximate for bodySmall
                    .italic()       // For FontStyle.Italic
            }
            .layoutPriority(1) // Helps this VStack take available space, similar to weight(1f)

        }
        .padding(16) // Padding for the entire item
        .frame(maxWidth: .infinity, alignment: .leading) // Ensures the HStack tries to fill width, content aligned left
        .background(
            RoundedRectangle(cornerRadius: 8)
                .fill(Color(UIColor.systemBackground)) // Adapts to light/dark mode
                .shadow(color: .gray.opacity(0.4), radius: 3, x: 0, y: 2) // Basic shadow for card effect
        )
    }
}


struct ErrorContentView: View {
    let message: String

    var body: some View {
        VStack(spacing: 16) {
            Text("😔")
                .font(.system(size: 48))
                .foregroundColor(.red)

//            Spacer.frame(height: 1=6)

            Text("An error occurred:")
                .font(.headline)

            Text(message)
                .font(.body)
                .foregroundColor(.red)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity) // Makes the VStack fill all available space.
        .padding(16)
    }
}

