import { APP_URL } from "../constans/AppConstans"

export type CalendarReadModel = Record<string, string[]>;

export async function getCalendar(
        startDate: string,
        endDate: string,
        durationMinutes: number
) : Promise<CalendarReadModel>
{
    const params = new URLSearchParams({
        startDate,
        endDate,
        durationMinutes: String(durationMinutes),
    });

    const response = await fetch(`${APP_URL}/calendar?${params}`);

    if (!response.ok) 
        throw new Error(`HTTP error: ${response.status}`); 

    return response.json();
}