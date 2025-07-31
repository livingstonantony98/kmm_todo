//
// Created by Philosopher on 25/07/25.
//

import ComposeApp

class IOSNativeResponseProvider: NativeResponseProvider {

    func getNativeResponse(input: Int32) -> Int32 {

        return input + 102
    }
}
