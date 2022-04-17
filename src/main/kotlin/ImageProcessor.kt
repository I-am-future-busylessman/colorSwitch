import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO

class ImageProcessor{
    private val utils = ImageProcessorUtils()
    private val validator = Validator()

    fun proceedImage(imagePath: String, type: String) {
        validator.validateType(type)
        try {
            val file = File(imagePath)
            val source = ImageIO.read(file)
            val result = BufferedImage(source.width, source.height, source.type)

            for (x in 0 until source.width) {
                for (y in 0 until source.height) {
                    result.setRGB(x, y, getConverted(Color(source.getRGB(x, y)), type).rgb)
                }
            }
            val output = File(imagePath.split(".")[0] + "_$type."+imagePath.split(".")[1])
            ImageIO.write(result, imagePath.split(".")[1], output)
        } catch (e: IOException) {
            println(e.printStackTrace())
        }
    }

    private fun getConverted(color: Color, type:String) : Color {
        val defaultRGB = utils.getRGB(color)
        val simulatedRGB = utils.simulateColorBlindness(type, defaultRGB.toLMS()).toRGB()
        val isolatedRGB = utils.calculateErrorMatrix(simulatedRGB, defaultRGB)
        val clampedRGB = utils.clampRGB(utils.addCompensation(utils.applyErrorModifications(isolatedRGB), defaultRGB))
        return Color(clampedRGB.R.toInt(), clampedRGB.G.toInt(), clampedRGB.B.toInt())
    }

    /*fun proceedImageToGrey(imagePath: String) {
        try {
            val file = File(imagePath)
            val source = ImageIO.read(file)
            val result = BufferedImage(source.width, source.height, source.type)

            for (x in 0 until source.width) {
                for (y in 0 until source.height) {
                    result.setRGB(x, y, getGrey(Color(source.getRGB(x, y))).rgb)
                }
            }
            val output = File(imagePath.split(".")[0] + "_grey."+imagePath.split(".")[1])
            ImageIO.write(result, "jpg", output)
        } catch (e: IOException) {
            println(e.printStackTrace())
        }
    }

    private fun getGrey(color: Color): Color {
        val grey = (color.red * 0.299 + color.green * 0.587 + color.blue * 0.114).toInt()
        return Color(grey, grey, grey)
    }*/
}