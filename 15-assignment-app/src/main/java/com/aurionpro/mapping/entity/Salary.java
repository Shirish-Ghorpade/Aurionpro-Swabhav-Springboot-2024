package com.aurionpro.mapping.entity;

import java.time.LocalDate;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Salary")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SalaryId")
    private int salaryId;

    @Column(name = "SalaryMonth")
    @NotNull(message = "Salary month must be specified")
    @Enumerated(EnumType.STRING)
    private Month salaryMonth;

    @Column(name = "GrossSalary")
    @DecimalMin(value = "0.0", inclusive = false, message = "Gross salary must be greater than 0")
    private double grossSalary;

    @Column(name = "Deductions")
    @PositiveOrZero(message = "Deductions must be zero or positive")
    private double deductions;

    @Column(name = "NetSalary")
    private double netSalary;

    @Column(name = "PaymentDate")
    @PastOrPresent(message = "Payment date must be in the past or present")
    private LocalDate paymentDate;

    @Column(name = "Status")
    @Enumerated(EnumType.STRING)
    private SalaryStatus status;

    // Ensure netSalary is correctly computed
    public double getNetSalary() {
        return grossSalary - deductions;
    }
}
