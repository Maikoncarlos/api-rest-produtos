package maikoncarlos.com.github.produtos.repository;

import maikoncarlos.com.github.produtos.models.Produto;
import maikoncarlos.com.github.produtos.resources.exception.ApiErrors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @RestControllerAdvice
    class AplicationControllerAdvice {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ApiErrors handleValidationErros(MethodArgumentNotValidException ex) {
            BindingResult bindingResult = ex.getBindingResult();
            List<String> messages = bindingResult.getAllErrors()
                    .stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.toList());
            return new ApiErrors(messages);

        }

        @ExceptionHandler(ResponseStatusException.class)
        public ResponseEntity handleReponseStatusException(ResponseStatusException ex) {
            String mensagemError = ex.getMessage();
            HttpStatus codigoStatus = ex.getStatus();
            ApiErrors apiErrors = new ApiErrors(mensagemError);
            return new ResponseEntity(apiErrors, codigoStatus);
        }

    }
}