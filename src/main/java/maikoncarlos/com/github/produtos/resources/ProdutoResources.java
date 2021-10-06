package maikoncarlos.com.github.produtos.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import maikoncarlos.com.github.produtos.models.Produto;
import maikoncarlos.com.github.produtos.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/")
@Api(value = "API REST Produtos")
@CrossOrigin(origins = "*")
public class ProdutoResources {

    @Autowired
    ProdutoService service;

    @GetMapping("/produtos")
    @ApiOperation(value = "Retorna uma lista de produtos")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<Produto> listaProdutos() {
        return service.getProdutos();
    }

    @GetMapping("/produto/{id}")
    @ApiOperation(value = "Retorna um produto especifico")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Produto listaUmProduto(@PathVariable Long id) {
        return service.getProdutoId(id);
    }

    @PostMapping("/produto")
    @ApiOperation(value = "Salva um produto")
    @ResponseStatus(HttpStatus.CREATED)
    public Produto salvaProduto(@RequestBody Produto produto) {
        return service.novoProduto(produto);
    }

    @PutMapping("/produto/{id}")
    @ApiOperation(value = "Atualiza um produto")
    public void atualizaProduto(@PathVariable Long id, @RequestBody Produto produto) {
         service.atualizaProduto(id, produto);
    }

    @PostMapping("/produto-delete/{id}")
    @ApiOperation(value = "Deleta um produto")
    public void deletaProduto(@PathVariable Long id ) {
        service.excluirProduto(id);
    }
}
