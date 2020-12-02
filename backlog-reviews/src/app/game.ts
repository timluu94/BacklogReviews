export interface Game {
    id: number;
    cover?: Cover;
    name: string;
    summary: string;
    imageData?: string; 
}

interface Cover {
    id: number;
    url: string;
}
