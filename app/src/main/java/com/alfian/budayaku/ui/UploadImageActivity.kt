package com.alfian.budayaku.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import com.alfian.budayaku.R
import com.alfian.budayaku.databinding.ActivityUploadImageBinding
import com.alfian.budayaku.helper.DataBudaya
import com.alfian.budayaku.ml.Budayaku
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer

class UploadImageActivity : AppCompatActivity() {

    //camera
    val REQUEST_IMAGE_CAPTURE = 1

    var bitmap: Bitmap? = null

    private lateinit var binding: ActivityUploadImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //camera
        binding.btnCamera.setOnClickListener {
            takePictureIntent()
        }

        val fileName = "labels_Budayaku.txt"

        val inputString = application.assets.open(fileName).bufferedReader().use { it.readText() }
        val townList = inputString.split("\n")

        binding.btnUpload.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, 100)
        }

        binding.btnPredict.setOnClickListener {

            bitmap?.let {

                val nilai = 360

                val resize = Bitmap.createScaledBitmap(bitmap!!, 180, 180, true)

                val model = Budayaku.newInstance(this)

// Creates inputs for reference.
                val inputFeature0 =
                    TensorBuffer.createFixedSize(intArrayOf(1, 180, 180, 3), DataType.UINT8)

                val tBuffer = TensorImage.fromBitmap(resize)
                val byteBuffer = tBuffer.buffer

                inputFeature0.loadBuffer(byteBuffer)

// Runs model inference and gets result.
                val outputs = model.process(inputFeature0)
                val outputFeature0 = outputs.outputFeature0AsTensorBuffer

                val max = getMax(outputFeature0.floatArray)

                binding.tvResult.text = townList[max]

                val hasil = townList[max]

                Log.d("aan", "onCreate: max = $max")


                when (max) {
                    0 -> binding.tvDeskripsi.text = getString(R.string.Baileo)
                    1 -> binding.tvDeskripsi.text = getString(R.string.Baloy_Mayo)
                    2 -> binding.tvDeskripsi.text = getString(R.string.belah_bubung)
                    3 -> binding.tvDeskripsi.text = getString(R.string.Bubungan_Lima)
                    4 -> binding.tvDeskripsi.text = getString(R.string.Dulohupa)
                    5 -> binding.tvDeskripsi.text = getString(R.string.Gapura_Candi_Bentar)
                    6 -> binding.tvDeskripsi.text = getString(R.string.Honai)
                    7 -> binding.tvDeskripsi.text = getString(R.string.Istana_Kesultanan_Kadariyah)
                    8 -> binding.tvDeskripsi.text = getString(R.string.Jabu_Bolon)
                    9 -> binding.tvDeskripsi.text = getString(R.string.Joglo)
                    10 -> binding.tvDeskripsi.text = getString(R.string.Keraton_Kesepuhan_Cirebon)
                    11 -> binding.tvDeskripsi.text = getString(R.string.Loka_Samawa)
                    12 -> binding.tvDeskripsi.text = getString(R.string.Mod_Aki_Aksa)
                }



// Releases model resources if no longer used.
                model.close()

            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100) {
            binding.ivImageChoose.setImageURI(data?.data)
            binding.ivImageChoose.visibility = View.VISIBLE
            val uri = data?.data
            bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
        }

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_IMAGE_CAPTURE -> {
                    val extras = data?.extras
                    val imageBitmap = extras?.get("data") as Bitmap
                    binding.ivImageChoose.setImageBitmap(imageBitmap)
                    binding.ivImageChoose.visibility = View.VISIBLE

                    bitmap = imageBitmap
                }
            }
        }
    }

    fun getMax(arr: FloatArray): Int {
        var index = 0
        var min = 0.0f
        for (i in 0..12) {
            if (arr[i] > min) {
                index = i
                min = arr[i]
            }
        }
        return index
    }

    private fun takePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }
}