package maikoncarlos.com.github.produtos.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import maikoncarlos.com.github.produtos.models.Produto;
import maikoncarlos.com.github.produtos.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/")
@Api(value = "API REST Produtos")
@CrossOrigin(origins = "*")
public class ProdutoResources {

    @Autowired
    ProdutoService service;

    @GetMapping("/produtos")
    @ApiOperation(value = "Retorna uma lista de produtos")
    public List<Produto> listaProdutos() {
        return service.getProdutos();
    }

    @GetMapping("/produto/{id}")
    @ApiOperation(value = "Retorna um produto especifico")
    public Produto listaUmProduto(@PathVariable @Valid Long id) {
        return service.getProdutoId(id);
    }

    @PostMapping("/produto")
    @ApiOperation(value = "Salva um produto")
    @ResponseStatus(HttpStatus.CREATED)
    public Produto salvaProduto(@RequestBody @Valid Produto produto)  {
        return service.novoProduto(produto);
    }

    @PutMapping("/produto")
    @ApiOperation(value = "Atualiza um produto")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizaProduto(@RequestBody @Valid Produto produto) {
        service.atualizaProduto(produto);

    }

    @PostMapping("/produto-delete/{id}")
    @ApiOperation(value = "Deleta um produto")//configuração para o swagger.
    @ResponseStatus(HttpStatus.NO_CONTENT)//status se não houver nenhum erro !!!
    public void deletaProduto(@PathVariable @Valid Long id) {
        service.excluirProduto(id);
    }
}
