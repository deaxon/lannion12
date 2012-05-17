class TrimestersController < ApplicationController
  def destroy    
    @trimester = Trimester.find(params[:id])
    @trimester.destroy
  end
end
