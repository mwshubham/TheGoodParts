package app.thegoodparts.extensions

import com.google.common.truth.Truth
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Performing boundary value analysis
 */
class CoreByteArrayExtensionsKtTest {

    @Before
    fun setUp() {
        println("setUp()")
    }

    @After
    fun tearDown() {
        println("tearDown()")
    }

    @Test
    fun toHex_minValue_returnTrue() {
        println("toHex_minValue_returnTrue()")
        println(byteArrayOf(-128).toHex())
        Truth.assertThat(byteArrayOf(-128).toHex()).isEqualTo("80")
    }

    @Test
    fun toHex_maxValue_returnTrue() {
        println("toHex_maxValue_returnTrue()")
        println(byteArrayOf(127).toHex())
        Truth.assertThat(byteArrayOf(127).toHex()).isEqualTo("7f")
    }

    @Test
    fun toHex_multiValue_returnTrue() {
        println("toHex_multiValue_returnTrue()")
        println(byteArrayOf(1, 2).toHex())
        Truth.assertThat(byteArrayOf(1, 2).toHex()).isEqualTo("0102")
    }
}