package glung.elm_architecture

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

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

