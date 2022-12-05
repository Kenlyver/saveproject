package com.example.tekotest

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tekotest.adapter.ProductAdapter
import com.example.tekotest.data.database.retrofit.RetrofitService
import com.example.tekotest.data.models.Product
import com.example.tekotest.data.models.ProductUpdate
import com.example.tekotest.data.source.AppRepository
import com.example.tekotest.databinding.ActivityMainBinding
import com.example.tekotest.utils.callback.ItemClick
import com.example.tekotest.utils.dialog.DialogProduct
import com.example.tekotest.utils.dialog.DialogShowProduct
import com.example.tekotest.viewmodel.ProductViewModel
import com.example.tekotest.viewmodel.ProductViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ProductViewModel
    private val adapter by lazy { ProductAdapter(itemClick) }
    private val dialogShowProduct = DialogShowProduct()
    private val dialogUpdate = DialogProduct() { product ->
        viewModel.updateProduct(product)
        val productUpdate = ProductUpdate(
            id = null,
            errorDescription = product.errorDescription,
            name = product.name,
            sku = product.sku,
            color = product.color,
            image = product.image
        )
        viewModel.insertUpdate(productUpdate)
    }
    private val itemClick = object : ItemClick<Product> {
        override fun onItemClick(t: Product) {
            dialogUpdate.showDialog(supportFragmentManager, t)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {
            rvProduct.adapter = adapter
            rvProduct.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            btnSubmit.setOnClickListener {
                var listUpdate = arrayListOf<ProductUpdate>()
                viewModel.getAllUpdate.observe(this@MainActivity) {
                    listUpdate = it as ArrayList<ProductUpdate>
                }
                dialogShowProduct.showDialog(supportFragmentManager, listUpdate)
            }
        }
        val retrofitService = RetrofitService.getInstance()
        val appRepository = AppRepository(retrofitService)

        viewModel = ViewModelProvider(
            this,
            ProductViewModelFactory(appRepository, application)
        ).get(ProductViewModel::class.java)
        viewModel.apply {
            productList.observe(this@MainActivity) { product ->
                colorList.observe(this@MainActivity) { color ->
                    product.forEach { productId ->
                        color.forEach {
                            if (productId.color != null) {
                                if (productId.color == it.id.toString()) {
                                    productId.color = it.name
                                }
                            } else {
                                productId.color = "null"
                            }
                        }
                    }
                }
                product.forEach {
                    insertProduct(it)
                }
                products.observe(this@MainActivity) {
                    adapter.submitData(it)
                }
            }
            errorMessage.observe(this@MainActivity) {
                Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
            }
            getAllProducts()
            getColors()
        }
    }

    override fun onDestroy() {
        viewModel.deleteAllUpdate()
        super.onDestroy()
    }
}