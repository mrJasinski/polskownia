import { useState } from "react";

function Appointments()
{
    const [currentDate, setCurrentDate] = useState(() => new Date());

    const { startDate, endDate } = getWeekStartAndEnd(currentDate);

    const options = ['Audyt językowy', 'Próbny egzamin ustny TELC', 'Próbny egzamin językowy państwowy', 'Rozmowa 30 min'];
    
    const [selected, setSelected] = useState("");

    function getWeekStartAndEnd(date : Date)
    {
        const day = date.getDay();

        const diff = day === 0 ? -6 : 1 - day;


        const firstDay = new Date(date);
        firstDay.setDate(date.getDate() + diff);

        const lastDay = new Date(firstDay);
        lastDay.setDate(date.getDate() + 6);

        return {
            startDate : firstDay,
            endDate : lastDay,
        };
    }

    return(
        <>
            <h2>Umów spotkanie:</h2>

            <label>Typ: </label>
            <select value = {selected} onChange = {(e) => setSelected(e.target.value)} >
                <option value="">Wybierz:</option>

                {options.map((option) => ( <option key={option} value={option}> {option} </option> ))}
            </select>

            <label>Terminarz:</label>
            <div>
                <button>{"<<"}</button>
                <label>{startDate.toLocaleDateString("pl-PL")} - {endDate.toLocaleDateString("pl-PL")}</label>
                <button>{">>"}</button>
            </div>
        </>
    );
}

export default Appointments;