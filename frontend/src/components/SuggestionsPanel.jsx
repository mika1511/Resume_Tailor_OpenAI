import React from 'react';

function SuggestionsPanel({ suggestions }) {
  return (
    <div>
      <h2 className="text-xl font-semibold mb-2">Suggestions</h2>
      {suggestions.length === 0 ? (
        <p className="text-gray-500">No suggestions yet.</p>
      ) : (
        <ul className="space-y-4">
          {suggestions.map((item, index) => (
            <li key={index} className="border p-4 rounded bg-blue-50 shadow-sm">
              <p><strong>Original:</strong> {item.original}</p>
              <p><strong>Suggested:</strong> {item.suggested}</p>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default SuggestionsPanel;
