import {University} from "./university"

export interface Professor {
    id: number;
    name: string;
}

export interface ProfessorDetail {
    id: number;
    name: string;
    education: string;
    age: number
    university: University
    experience: number
}
