package gov.med.security_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "role_tbl", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"name"}, name = "uk_role_name")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role extends AbstractBaseEntity {
	@Column(name = "name", nullable = false, length = 100)
	private String name;

	@Column(name = "description")
	private String description;
}
