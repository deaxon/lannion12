class PdfUploadsController < ApplicationController
  # DELETE /pdf_uploads/1
  # DELETE /pdf_uploads/1.json
  def destroy
    @pdf_upload = PdfUpload.find(params[:id])
    @pdf_upload.destroy

    respond_to do |format|
      format.html { redirect_to pdf_uploads_url }
      format.json { head :no_content }
    end
  end
end
