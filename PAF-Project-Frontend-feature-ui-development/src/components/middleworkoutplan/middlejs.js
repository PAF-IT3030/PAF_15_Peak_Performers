document.addEventListener("DOMContentLoaded", () => {
    const fileInput = document.getElementById("file-input");
    const icon = document.querySelector('.uil-paperclip');
    const preview = document.getElementById('preview');

    icon.addEventListener('click', () => {
      fileInput.click();
    });
    const handleFileInputChange = () => {
      const file = fileInput.files[0];
      const reader = new FileReader();
  
      reader.addEventListener('load', () => {
        const img = document.createElement('img');
        img.src = reader.result;
        preview.innerHTML = '';
        preview.appendChild(img);
  
        const closeIcon = document.createElement('span');
        closeIcon.innerHTML = '<i className="uil uil-times"></i>';
        closeIcon.style.position = 'absolute';
        closeIcon.style.top = '0';
        closeIcon.style.right = '0';
        closeIcon.style.cursor = 'pointer';
        preview.appendChild(closeIcon);
  
        closeIcon.addEventListener('click', () => {
          preview.innerHTML = '';
          fileInput.value = null;
        });
      });
  
      if (file) {
        reader.readAsDataURL(file);
      }
    };
  
    const handleIconClick = () => {
      fileInput.click();
    };
  
    icon.addEventListener('click', handleIconClick);
    fileInput.addEventListener('change', handleFileInputChange);
  
    // Cleanup function to remove event listeners
    return () => {
      icon.removeEventListener('click', handleIconClick);
      fileInput.removeEventListener('change', handleFileInputChange);
    };
  });
