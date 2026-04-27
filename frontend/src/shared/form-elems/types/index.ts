import type {InputHTMLAttributes} from "react";

export interface BaseInputProps extends InputHTMLAttributes<HTMLInputElement> {
    label: string;
}