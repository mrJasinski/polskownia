import { useEffect, useState } from "react";
import { type CalendarReadModel, getCalendar } from "../../services/CalendarService";

export default function Calendar() {
  const [availableTimes, setAvailableTimes] = useState<CalendarReadModel>({});
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    getCalendar(
        // TODO hardcode do testu
        "2026-10-20"
        , "2026-10-25"
        ,90
    )
      .then(data => {
        setAvailableTimes(data);
      })
      .catch(err => {
        setError(err.message);
      })
      .finally(() => {
        setLoading(false);
      });
  }, []);

  if (loading) {
    return <p>Ładowanie dostępnych godzin...</p>;
  }

  if (error) {
    return <p>Błąd: {error}</p>;
  }

  const sortedAvailableTimes = Object.entries(availableTimes)
  .sort(([dateA], [dateB]) => new Date(dateA).getTime() - new Date(dateB).getTime());

  return (
    <main>
      <h1>Dostępne terminy</h1>
     
      <table>
        <thead>
          <tr>
            {sortedAvailableTimes.map(([date]) => (
              <th key={date}>{date}</th>
            ))}
          </tr>
        </thead>

        <tbody>
          {Array.from({
            length: Math.max(
              0,
              ...sortedAvailableTimes.map(([, times]) => times.length)
            )
          }).map((_, rowIndex) => (
            <tr key={rowIndex}>
              {sortedAvailableTimes.map(([date, times]) => (
                <td key={date}>
                  {times[rowIndex] && (
                    <button
                      key={times[rowIndex]}
                      type="button"
                      onClick={() => {
                        console.log(
                          "Wybrany termin:",
                          date,
                          times[rowIndex]
                        );
                      }}
                      style={{ margin: "4px" }}
                    >
                      {times[rowIndex].slice(0, 5)}
                    </button>
                  )}
                </td>
              ))}
            </tr>
          ))}
        </tbody>
      </table>

    </main> 
  );
}