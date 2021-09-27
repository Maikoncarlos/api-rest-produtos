package maikoncarlos.com.github.produtos.repository;

import maikoncarlos.com.github.produtos.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}