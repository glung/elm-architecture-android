package glung.elm_architecture

import android.content.Context
import android.widget.LinearLayout
import org.jetbrains.anko.button
import org.jetbrains.anko.onClick
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout

sealed class Action {
    object INIT : Action()

    object UP : Action()

    object DOWN : Action()
}

data class Model(val counter: Int = 0)

fun update(action: Action, model: Model): Model {
    return when (action) {
        is Action.INIT -> Model()
        is Action.UP -> Model(model.counter + 1)
        is Action.DOWN -> Model(model.counter - 1)
    }
}

fun view(context: Context, model: Model, dispatch : (Action) -> Unit): LinearLayout {
    with(context) {
        return verticalLayout {
            button("up") {
                onClick { dispatch(Action.UP) }
            }
            button("down") {
                onClick { dispatch(Action.DOWN) }
            }
            textView(model.counter.toString())
        }
    }
}
