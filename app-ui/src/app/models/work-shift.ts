import { Ususario } from "./usuario";

export class WorkShift {
    id?: number;
    user!: Ususario;
    date!: string; 
    workedHours!: string; 
    overtimeHours?: string;
  }
  