import React from 'react';

function JDInput({ jdText, setJDText }) {
  return (
    <div className="p-4">
      <h2 className="text-xl font-semibold mb-2">Job Description</h2>
      <textarea
        value={jdText}
        onChange={(e) => setJDText(e.target.value)}  // <-- make sure this matches
        className="w-full h-40 border rounded p-2"
        placeholder="Paste job description here"
      />
    </div>
  );
}

export default JDInput;
