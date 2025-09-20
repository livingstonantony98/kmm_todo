//
// Created by Philosopher on 25/07/25.
//

import ComposeApp
import shared

class IOSNativeResponseProvider: NativeResponseProvider {

    func getNativeResponse(input: Int32) -> Int32 {

        return input + 102
    }
}
