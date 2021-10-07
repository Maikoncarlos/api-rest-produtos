package maikoncarlos.com.github.produtos.service;

import maikoncarlos.com.github.produtos.models.Produto;
import maikoncarlos.com.github.produtos.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    public Produto novoProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void
    atualizaProduto(Long id, Produto produtoatualizado) {
        produtoRepository.findById(id).map(produto -> {
                    produto.setNome(produtoatualizado.getNome());
                    produto.setQuantidade(produtoatualizado.getQuantidade());
                    produto.setValor(produtoatualizado.getValor());
                    return produtoRepository.save(produto);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    public void excluirProduto(Long id) {
        produtoRepository.findById(id).map(produto -> {
                    produtoRepository.delete(produto);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

    }

}

