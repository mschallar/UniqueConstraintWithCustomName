package com.example.UniqueConstraintWithCustomNameDemo;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@SpringBootApplication
public class UniqueConstraintWithCustomNameDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniqueConstraintWithCustomNameDemoApplication.class, args);
	}

	@Entity
	@Table(name = "my_entity",
		uniqueConstraints = {
			@UniqueConstraint(
					columnNames = { "my_other_entity_id" },
					name = "UNIQUE_myOtherEntity" // <-- this name is ignored!
			)
		},
		indexes = {
				@Index(columnList = "someLong", name = "IDX_someLong") // <-- this name is not ignored!
		}
	)
	public static class MyEntity {
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
		@GenericGenerator(name = "native", strategy = "native")
		long id;
		
		@OneToOne(fetch = FetchType.LAZY, optional = false)
		@JoinColumn(updatable = false, foreignKey = @ForeignKey(name = "FK_moe"))
		private MyOtherEntity myOtherEntity;
		
		@Column
		private long someLong;
		
	}
	
	@Entity
	@Table(name = "my_other_entity")
	public static class MyOtherEntity {
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
		@GenericGenerator(name = "native", strategy = "native")
		long id;
		
		@Column
		private String someString;
		
	}
	
}
