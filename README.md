# elm-architecture-android

An example of the [ELM architecture](https://github.com/evancz/elm-architecture-tutorial/) applied ot Android using [Kotlin](kotlinlang.org) with [Anko](https://github.com/JetBrains/anko).

# Activity

The [MainActivity](app/src/main/java/glung/elm_architecture/MainActivity.kt) defines a dispatch method (outside of its scope). 

The `dispatch` method : 
- `update` the model
- `render` a view for the updated model
- `setContentView` with the view representing the updated model.

`onCreate` the activity initialize the system with an `INIT` action and a new `Model`.  


```
public class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dispatch(this)
    }

}

fun dispatch(activity: Activity, action: Action = Action.INIT, model: Model = Model()) {
    val updatedModel = update(action, model)
    val dispatch = dispatch(activity, updatedModel)

    activity.setContentView(view(activity, updatedModel, dispatch))
}

fun dispatch(activity: Activity, model: Model) = { newAction: Action ->
    dispatch(activity, newAction, model)
}

```

# The Counter


All the application logic is in [Counter.kt](app/src/main/java/glung/elm_architecture/counter.kt). 

The file 
- declares the `Model`
- declares the `Action`
- implements the `update` function 
- implements the`view` function 

```
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
```

# Note 

This is experimental and made for a talk. Feedback welcomed.

[@lungos](https://twitter.com/Lungos)
