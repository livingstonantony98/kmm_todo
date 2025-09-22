//
// Created by Philosopher on 08/08/25.
//


import Foundation
import SwiftUI
import shared



struct TodoAppScreenView: View {
    
    // @StateObject ensures the ViewModel's lifecycle is tied to this view's lifecycle.
    // Use @ObservedObject if the ViewModel is created and managed by a parent and passed in.
    //    @StateObject private var viewModel: MyViewModel = MyViewModel()
    
    @StateObject var viewModelStoreOwner = IOSViewModelStoreOwner()
    @EnvironmentObject var appContainer: ObservableValueWrapper<AppContainer>
    
    var body: some View {
        
        let mainViewModel: MyViewmodel = viewModelStoreOwner.viewModel(
            factory: appContainer.value.todoViewModelFactory
        )
        
        NavigationStack {
            Observing(mainViewModel.uiState) { uiState in // uiState here is the actual value from the flow
                // The `uiState` from `Observing` will be the Kotlin sealed class instance
                // You need to switch on its type. Kotlin/Native generates specific names.
                // For a sealed class `UiState`, the cases might be `UiStateLoading`, `UiStateSuccess`, `UiStateError`.
                // Or, if your KMP library provides helpers, it might be more direct.
                
                // Assuming direct casting or type checking works for the generated Swift enum
                if uiState is UiStateLoading { // Check specific generated type
                    VStack { // Equivalent to Column
                        ProgressView() // Equivalent to CircularProgressIndicator
                        Text("Loading todos...")
                            .padding(.top, 16)
                    }
                    .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .center) // .align(Alignment.Center)
                } else if let successState = uiState as? UiStateSuccess<NSArray> { // Assuming List<TodoItem> becomes NSArray
                    // You might need to cast `successState.data` to the correct Swift array type
                    let todos = successState.data as? [Todo] ?? []
                    if todos.isEmpty {
                        Text("No todos found. Add Some!")
                            .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .center)
                    } else {
                        TodoListContentView(todos: todos)
                            .frame(maxWidth: .infinity, maxHeight: .infinity) // .fillMaxSize()
                    }
                } else if let errorState = uiState as? UiStateError {
                    ErrorContentView(message: errorState.message)
                        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .center) // Center error content
                } else {
                    // Fallback or initial state if UiState is something else or not yet loaded
                    Text("Unhandled state or initializing...")
                        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .center)
                }
            }
            .navigationTitle("Todos")
        }
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

