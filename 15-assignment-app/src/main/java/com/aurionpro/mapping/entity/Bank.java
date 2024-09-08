package com.aurionpro.mapping.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "Bank")
public class Bank {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bankId")
    private int bankId;

    @Column(name = "bankName")
    @NotNull(message = "Bank name is mandatory")
    @Pattern(regexp = "^[A-Za-z]*$", message = "Invalid Input")
    @Size(min = 2, max = 50, message = "Bank name must be between 2 and 50 characters")
    private String bankName;

    @Column(name = "ifscNumber")
    @NotNull(message = "IFSC number is mandatory")
	@Pattern(regexp = "^[A-Z]{4}0[A-Z0-9]{6}$")
    @Size(min = 11, max = 11, message = "IFSC number must be exactly 11 characters long")
    private String ifscNumber;
    
    @OneToMany(mappedBy = "bank")
    private List<SalaryAccount> salaryAccounts;
}
