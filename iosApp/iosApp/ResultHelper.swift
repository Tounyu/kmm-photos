//
//  ResultHelper.swift
//  iosApp
//
//  Created by tounyu on 2023/07/08.
//  Copyright © 2023 tounyu.dev. All rights reserved.
//

import Foundation
import shared

public enum Result<T : AnyObject> {
    case success(T)
    case error(KotlinThrowable)
    
    public init(_ obj: KmmResult) {
        if let obj = obj as? KmmResultSuccess<T> {
            self = .success(obj.data)
        } else if let obj = obj as? KmmResultError {
            self = .error(obj.throwable)
        } else {
            fatalError("KmmResult not syncronized with Result")
        }
    }
}
