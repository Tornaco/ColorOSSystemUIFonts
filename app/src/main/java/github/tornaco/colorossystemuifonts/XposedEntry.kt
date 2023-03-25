package github.tornaco.colorossystemuifonts

import android.graphics.Typeface
import de.robv.android.xposed.IXposedHookZygoteInit

class XposedEntry : IXposedHookZygoteInit {
    private val logger = Logger("XposedEntry")

    override fun initZygote(startupParam: IXposedHookZygoteInit.StartupParam?) {
        hookTypeface()
    }

    private fun hookTypeface() {
        afterMethod(Typeface::class.java, "createFromAsset") {
            if (it.args[1].toString().contains("OplusSans")) {
                it.result = Typeface.DEFAULT
                logger.w("createFromAsset, Set typeface to DEFAULT")
            }
        }

        afterMethod(Typeface.Builder::class.java, "build") {
            it.result = Typeface.DEFAULT
            logger.w("build, Set typeface to DEFAULT")
        }
    }
}
