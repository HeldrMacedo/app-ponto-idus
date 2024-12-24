import { Ususario } from "./usuario";

export enum TypeRegister {
    EXIT = 'EXIT',
	ENTRY = 'ENTRY'
}

export class TimeClockRecord {
    id?: number; 
    user!: Ususario; 
    dateHours!: string;
    tipo!: TypeRegister;
}