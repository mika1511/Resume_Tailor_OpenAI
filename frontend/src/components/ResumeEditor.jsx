import React from 'react';

function ResumeEditor({ resumeLines, setResumeLines }) {
  const handleChange = (e) => {
    const lines = e.target.value.split('\n');
    setResumeLines(lines);
  };

  return (
    <div>
      <h2 className="text-xl font-semibold mb-2">Resume</h2>
      <textarea
        value={resumeLines.join('\n')}
        onChange={handleChange}
        className="w-full h-40 border rounded p-2"
        placeholder="Paste or write your resume line-by-line here"
      />
    </div>
  );
}

export default ResumeEditor;
