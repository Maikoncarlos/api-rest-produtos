package maikoncarlos.com.github.produtos.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data //anotacion Lombok - que cria os getter e setter, hascode ,somente com essa anotacion
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_PRODUTO")
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(nullable = false,name = "produto", length = 150)
    @NotEmpty(message = "campo nomeProduto obrigatório")
    private String nomeProduto;


    @Column(nullable = false, name = "qtdd_estoque", length = 10)
    @NotNull(message = "campo quantidade obrigatório")
    private Integer quantidadeEmEstoque;


    @Column(nullable = false, name = "valor_produto", length = 10)
    @NotNull(message = "campo valoProduto é obrigatório")
    private BigDecimal valorDoProduto;


}
