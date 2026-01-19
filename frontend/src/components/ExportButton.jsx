import React from 'react';
import { jsPDF } from 'jspdf';

function ExportButton({ resumeLines }) {
  const exportToPDF = () => {
    const doc = new jsPDF();
    resumeLines.forEach((line, i) => doc.text(line, 10, 10 + i * 10));
    doc.save("tailored_resume.pdf");
  };

  return (
    <button onClick={exportToPDF} className="bg-green-600 text-white px-4 py-2 rounded m-4">
      Export PDF
    </button>
  );
}

export default ExportButton;
