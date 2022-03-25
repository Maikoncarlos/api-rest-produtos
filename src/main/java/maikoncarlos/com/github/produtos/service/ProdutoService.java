package maikoncarlos.com.github.produtos.service;

import maikoncarlos.com.github.produtos.models.Produto;
import maikoncarlos.com.github.produtos.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    public List<Produto> getProdutos() {
        return produtoRepository.findAll();
    }

    public Produto getProdutoId(Long id) {
        return produtoRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NO_CONTENT, "Cliente não encontrado"));
    }

    public Produto novoProduto(@Valid Produto produto) {
        return produtoRepository.save(produto);
    }

    public void
    atualizaProduto(@Valid Produto produtoatualizado) {
        produtoRepository.findById(Long.valueOf(produtoatualizado.getId())).map(produto -> {
            produto.setNomeProduto(produtoatualizado.getNomeProduto());
            produto.setQuantidadeEmEstoque(produtoatualizado.getQuantidadeEmEstoque());
            produto.setValorDoProduto(produtoatualizado.getValorDoProduto());
            return produtoRepository.save(produto);
        });
    }

    public void excluirProduto(Long id) {
        produtoRepository.findById(id).map(produto -> {
            produtoRepository.delete(produto);
            return Void.TYPE;
        });
    }
}