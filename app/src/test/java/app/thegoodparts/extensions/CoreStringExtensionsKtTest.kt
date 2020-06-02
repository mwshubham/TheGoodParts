package app.thegoodparts.extensions

import com.google.common.truth.Truth
import org.junit.After
import org.junit.Before
import org.junit.Test

class CoreStringExtensionsKtTest {
    @Before
    fun setUp() {
        println("setUp()")
    }

    @After
    fun tearDown() {
        println("tearDown()")
    }

    @Test
    fun toMD5_isEqualTo() {
        println("toMD5_isEqualTo()")
        println("Android".toMD5())
        Truth.assertThat("Android".toMD5()).isEqualTo("e84e30b9390cdb64db6db2c9ab87846d")
    }

    @Test
    fun toMD5_isNotNull() {
        println("toMD5_isNotNull()")
        println("Android".toMD5())
        Truth.assertThat("Android".toMD5()).isNotNull()
    }

    @Test
    fun toMD5_isNotEmpty() {
        println("toMD5_isNotEmpty()")
        println("Android".toMD5())
        Truth.assertThat("Android".toMD5()).isNotEmpty()
    }
}