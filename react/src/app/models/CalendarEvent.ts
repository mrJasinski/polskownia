export interface GoogleDate {
  value: number;
  timeZone?: string;
  dateOnly?: boolean;
}

export interface CalendarEvent 
{
  id: string;
  summary?: string;
  location?: string;
  htmlLink?: string;
  start?: 
    {
      dateTime?: GoogleDate | string;
      date?: string;
    };
  end?: 
    {
      dateTime?: GoogleDate |string;
      date?: string;
    };
}