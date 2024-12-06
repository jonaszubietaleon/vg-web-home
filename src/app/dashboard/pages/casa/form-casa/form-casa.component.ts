import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { CasaService } from '../../services/casa.service';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';

@Component({
  selector: 'app-form-casa',
  templateUrl: './form-casa.component.html',
  styleUrls: ['./form-casa.component.css'],
  standalone: true,
  providers: [CasaService],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatInputModule,
    MatFormFieldModule,
    MatButtonModule,
    MatDialogModule,
  ],
})
export class FormCasaComponent implements OnInit {
  casaForm?: FormGroup;
  step = 1;
  formData: any = null;

  constructor(
    private fb: FormBuilder,
    private casaService: CasaService,
    private dialogRef: MatDialogRef<FormCasaComponent>
  ) {
    this.initializeForm();
  }

  ngOnInit(): void {
    // No es necesario hacer nada aquí
  }

  initializeForm(): void {
    this.casaForm = this.fb.group({
      nombre: new FormControl(''),
      direccion: new FormControl(''),
      estado: new FormControl('A'), // Por defecto, "Activo"
    });

    if (this.formData) {
      this.casaForm.patchValue(this.formData);
    }
  }

  nextStep(): void {
    if (this.step < 1 && this.casaForm) {
      this.formData = this.casaForm.value;
      this.step++;
    }
  }

  previousStep(): void {
    if (this.step > 1 && this.casaForm) {
      this.step--;
      this.casaForm.patchValue(this.formData);
    }
  }

  submitForm(): void {
    if (this.casaForm) {
      const casaData = {
        id_casa: 0, // Aquí asignarías el ID de la casa si fuera necesario.
        nombre: this.casaForm.value.nombre,
        direccion: this.casaForm.value.direccion,
        estado: this.casaForm.value.estado, // "A" o "I" según lo seleccionado
      };

      this.casaService.registerCasa(casaData).subscribe({
        next: () => {
          console.log('Formulario enviado con éxito');
          this.dialogRef.close(true);
        },
        error: (error: any) => {
          console.error('Error al registrar la casa:', error);
        },
      });
    }
  }

  cancel(): void {
    this.dialogRef.close(false);
  }
}
