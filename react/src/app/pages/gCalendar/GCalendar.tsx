import { useEffect, useState } from "react";
import type { CalendarEvent, GoogleDate } from "../../models/CalendarEvent";

import FullCalendar from "@fullcalendar/react";
import timeGridPlugin from "@fullcalendar/react/timegrid";
import interactionPlugin from "@fullcalendar/react/interaction";
import plLocale from "@fullcalendar/react/locales/pl";
import type { EventInput } from "@fullcalendar/react";

import "@fullcalendar/react/skeleton.css";
import "@fullcalendar/react/themes/classic/theme.css";
import "@fullcalendar/react/themes/classic/palette.css";
import themePlugin from "@fullcalendar/react/themes/classic";

//import "./GCalendar.css";

interface GoogleEventsResponse {
  items?: CalendarEvent[];
}

export default function GCalendar() 
{
  const [events, setEvents] = useState<EventInput[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => 
    {
    fetch("http://localhost:8080/g-calendar")
      .then((response) => 
        {
          if (!response.ok) 
            throw new Error(`Błąd HTTP: ${response.status}`);
            
          return response.json() as Promise<GoogleEventsResponse>;
      })
      .then((data) => 
        {

            const toDate = 
              (
                value? : GoogleDate | string,
                date? : string
              ): string | undefined => 
                  {
                    if (date) return date;
                    if (!value) return undefined;

                    if (typeof value === "string") return value;

                    return new Date(value.value).toISOString();
                  };

console.log("API response:", data);
console.log("Items:", data.items);

        const mappedEvents = (data.items ?? []).map(
            (event: CalendarEvent) => ({
                id: event.id,
                title: event.summary ?? "Bez tytułu",
                start: toDate(event.start?.dateTime, event.start?.date),
                end: toDate(event.end?.dateTime, event.end?.date),
                allDay: Boolean(event.start?.date),
                url: event.htmlLink,
            })
            );

            setEvents(mappedEvents);
      })
      .catch((err: Error) => setError(err.message))
      .finally(() => setLoading(false));
  }, []);

  return (
    <main className = "calendar-page">
      <header className = "calendar-heading">
        <div>
          <h1>Mój kalendarz</h1>
          <p>Twoje wydarzenia z Google Calendar</p>
        </div>
        <span className = "calendar-status">
          <span className = "status-dot" />
          Google Calendar
        </span>
      </header>

      {loading && <p className="calendar-message">Ładowanie wydarzeń...</p>}

      {error && (
        <p className="calendar-error" role="alert">
          Nie udało się pobrać wydarzeń: {error}
        </p>
      )}

      {!loading && !error && (
        <section className = "calendar-card">
          <FullCalendar
            plugins = 
              {[
                themePlugin
                , timeGridPlugin
                , interactionPlugin
              ]}
            initialView = "timeGridWeek"
            locale = {plLocale}
            firstDay = {1}
            weekends = {true}
            allDaySlot = {true}
            nowIndicator = {true}
            height = "auto"
            expandRows = {true}
            slotMinTime = "07:00:00"
            slotMaxTime = "22:00:00"
            slotDuration = "00:30:00"
            scrollTime = "08:00:00"
            headerToolbar =
              {{
                left: "prev,next today",
                center: "title",
                right: "",
              }}
            buttons =
              {{
                today: {text : "Dzisiaj"},
              }}
            events = {events}
            eventClick = {(info) => 
              {
                if (info.event.url) 
                {
                  info.jsEvent.preventDefault();
                  window.open(info.event.url, "_blank", "noopener,noreferrer");
                }
              }}
            eventTimeFormat =
              {{
                hour: "2-digit",
                minute: "2-digit",
                hour12: false,
              }}
            slotHeaderFormat =
              {{
                hour: "2-digit",
                minute: "2-digit",
                hour12: false,
              }}
          />
        </section>
      )}
    </main>
  );
}