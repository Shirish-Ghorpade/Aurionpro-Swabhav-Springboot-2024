package com.aurionpro.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "documents")
public class Document{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "documentId")
    private Long documentId;

    @Column(name = "documentName")
    private String documentName;

    @Column(name = "documentUrl")
    private String documentUrl;
    
    
    @Enumerated(EnumType.STRING)
    KycStatus kycStatus;
    
    @Enumerated(EnumType.STRING)
    DocumentType documentType;
    
    @ManyToOne
    @JoinColumn(name="customerId")
    private Customer customer;
   
   

}