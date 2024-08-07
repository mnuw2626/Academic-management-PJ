/*
document.addEventListener('DOMContentLoaded', () => {
    const startTime = 9;  // 시작 시간
    const endTime = 18;   // 종료 시간
    const interval = 0.5; // 30분 간격
    const days = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday'];

    const createTable = () => {
        const tableContainer = document.getElementById('table-container');
        const table = document.createElement('table');
        const tbody = document.createElement('tbody');

        // Create header row
        const headerRow = document.createElement('tr');
        headerRow.appendChild(document.createElement('th')); // Top-left corner cell
        days.forEach(day => {
            const th = document.createElement('th');
            th.textContent = day;
            headerRow.appendChild(th);
        });
        table.appendChild(headerRow);

        // Create time rows
        for (let hour = startTime; hour < endTime; hour++) {
            for (let halfHour = 0; halfHour < 2; halfHour++) {
                const time = `${String(hour).padStart(2, '0')}:${String(halfHour * 30).padStart(2, '0')}`;
                const row = document.createElement('tr');
                const timeCell = document.createElement('td');
                timeCell.textContent = time;
                row.appendChild(timeCell);

                days.forEach(() => {
                    const cell = document.createElement('td');
                    cell.textContent = ''; // Empty cell for now
                    row.appendChild(cell);
                });

                tbody.appendChild(row);
            }
        }

        table.appendChild(tbody);
        tableContainer.appendChild(table);
    };

    createTable();
});
*/