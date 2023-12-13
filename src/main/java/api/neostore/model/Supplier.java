package api.neostore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import api.neostore.model.constraint.CnpjConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Supplier {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Preenchimento do nome é obrigatório")
	private String name;

	@Email(message = "Utilize um formato de e-mail válido")
	@NotBlank(message = "Preenchimento do e-mail é obrigatório")
	private String email;

	@NotBlank(message = "Preenchimento da descrição é obrigatório")
	private String description;

	@CnpjConstraint
	@NotBlank(message = "Preenchimento do CNPJ é obrigatório")
	private String cnpj;
}
