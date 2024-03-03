import { NgModel } from "@angular/forms";
import {MatIconModule} from '@angular/material/icon';
import {MatDividerModule} from '@angular/material/divider';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule} from '@angular/forms';
import {MatSnackBar} from '@angular/material/snack-bar';
import {MatListModule} from '@angular/material/list';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatRadioModule} from '@angular/material/radio';
import {
    MatDialog,
    MAT_DIALOG_DATA,
    MatDialogRef,
    MatDialogTitle,
    MatDialogContent,
    MatDialogActions,
    MatDialogClose,
  } from '@angular/material/dialog';
import {MatTableModule} from '@angular/material/table';
import { NgModule } from "@angular/core";

@NgModule({
    exports:[
        MatListModule,
        MatProgressBarModule,
        MatDatepickerModule,
        MatButtonModule,
        MatIconModule,
        MatDividerModule,
        MatCardModule,
        MatToolbarModule,
        MatInputModule,
        MatFormFieldModule,
        FormsModule,
        MatCardModule,
        MatTableModule,
        MatRadioModule,
        
    ]
})
export class DemoAngularmaterialModule{

}