class SemestersController < ApplicationController
  def destroy
    @semester = Semester.find(params[:id])
    @semester.destroy
  end
end
