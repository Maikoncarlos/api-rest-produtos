package maikoncarlos.com.github.produtos.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import maikoncarlos.com.github.produtos.models.Produto;
import maikoncarlos.com.github.produtos.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "api/")
@Api(value = "API REST Produtos")
@CrossOrigin(origins = "*")
public class ProdutoResources {

    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping("/produtos")
    @ApiOperation(value = "Retorna uma lista de produtos")
    public List<Produto> listaProdutos() {
        return produtoRepository.findAll();
    }

    @GetMapping("/produto/{id}")
    @ApiOperation(value = "Retorna um produto especifico")
    public Produto listaUmProduto(@PathVariable Long id) {
        return produtoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não Encontrado"));
    }

    @PostMapping("/produto")
    @ApiOperation(value = "Salva um produto")
    public Produto salvaProduto(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    @PutMapping("/produto")
    @ApiOperation(value = "Atualiza um produto")
    public Produto atualizaProduto(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    @PostMapping("/produto-delete")
    @ApiOperation(value = "Deleta um produto")
    public void deletaProduto(@RequestBody Produto produto) {
        produtoRepository.delete(produto);
    }
}
