package gov.med.security_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_tbl", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"email"}, name = "uk_user_email")
}, indexes = {
		@Index(columnList = "first_name", name = "idx_users_name"),
		@Index(columnList = "last_name", name = "idx_users_last_name")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractBaseEntity {
	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "first_name", nullable = false, length = 100)
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 100)
	private String lastName;

	@Column(name = "password", nullable = false, length = 100)
	private String password;

	@Column(name = "avatar", columnDefinition = "text")
	private String avatar;

}
